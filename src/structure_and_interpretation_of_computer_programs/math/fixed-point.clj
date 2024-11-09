(require '[clojure.math :as math])

(def tolerance 0.000001)

(defn close-enough? [v1 v2]
  (< (abs (- v1 v2)) tolerance))

(defn fixed-point [f first-guess]
  (letfn [(try-fn [guess]
            (let [next (f guess)]
              (if (close-enough? guess next)
                next
                (recur next))))]
    (try-fn first-guess)))

;(defn fixed-point [f first-guess]
;  (defn close-enough? [v1 v2]
;    (< (abs (- v1 v2)) tolerance))
;  (defn try-fn [guess]
;    (let [next (f guess)]
;      (if (close-enough? guess next)
;        next
;        (try-fn next))))
;  (try-fn first-guess))

(defn fixed-point-of-transform [g transform guess]
  (fixed-point (transform g) guess))

(defn average [numbers] (/ (apply + numbers) (count numbers)))
(defn sqrt [x]
  (fixed-point  (fn [y] (average  [y (/ x y)]))   1.0))

(defn golden-ratio []
  (fixed-point  (fn [x] (+ 1 (/ 1 x))) 1.0))

(defn average-damp [f]
  (fn [x] (average [x (f x)])))

(defn sqrt2 [x]
  (fixed-point (average-damp (fn [y] (/ x y))) 1.0))

(defn cube-root [x]
  (fixed-point (average-damp (fn [y] (/ x (* y y)))) 1.0))

(def dx 0.000001)

(defn deriv [g]
  (fn [x] (/ (- (g (+ x dx)) (g x)) dx)))

(defn cube [x] (* x x x))

(defn newton-transform [g]
  (fn [x] (- x (/ (g x) ((deriv g) x)))))

(defn newton-method [g guess]
  (fixed-point (newton-transform g) guess))

(defn sqrt3 [x]
  (newton-method (fn [y] (- (* y y) x)) 1.0))

(defn sqrt4 [x]
  (fixed-point-of-transform (fn [y] (/ x y)), average-damp 1.0))

(defn -main []
  (println (fixed-point math/cos 1.0))
  (println (fixed-point (fn [y] (+ (math/sin y) (math/cos y))) 1.0))
  (println "_______")

  (println (sqrt 2))
  (println (sqrt2 2))
  (println (sqrt3 2))
  (println (sqrt4 2))
  (println "_______")

  (println (golden-ratio))
  (println "_______")

  (println (fixed-point (fn [x] (/ (math/log 1000) (math/log x))) 2))
  (println (fixed-point (fn [x] (average [x (/ (math/log 1000) (math/log x))])) 2))
  (println "_______")

  (println (cube-root 9))
  (println ((deriv cube) 5))
  (println "_______")
  )