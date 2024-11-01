(require '[clojure.math :as math])

(defn average [numbers] (/ (apply + numbers) (count numbers)))
(defn close-enough? [x y]
  (< (abs (- x y)) 0.001))

(defn search [f neg-point pos-point]
  (let [midpoint (average [neg-point pos-point])]
    (if (close-enough? neg-point pos-point)
      midpoint
      (let [test-value (f midpoint)]
        (cond
          (pos? test-value) (search f neg-point midpoint)
          (neg? test-value) (search f midpoint pos-point)
          :else midpoint)))))

(defn half-interval-method [f a b]
  (let [a-value (f a) b-value (f b)]
    (cond
      (and (neg? a-value) (pos? b-value)) (search f a b)
      (and (neg? b-value) (pos? a-value)) (search f b a)
      :else (throw  (Throwable. "values are not of opposite sign")))))

(println (half-interval-method math/sin 2.0 4.0))