(require '[clojure.math :as math])

(def tolerance 0.00001)

(defn close-enough? [v1 v2]
  (< (Math/abs (- v1 v2)) tolerance))

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

(defn average [numbers] (/ (apply + numbers) (count numbers)))
(defn sqrt [x]
  (fixed-point  (fn [y] (average  [y (/ x y)]))   1.0))

(println (fixed-point math/cos 1.0))
(println (fixed-point (fn [y] (+ (math/sin y) (math/cos y))) 1.0))
(println (sqrt 2))