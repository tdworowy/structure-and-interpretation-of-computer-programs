
(def dx 0.000001)

(defn average [numbers] (/ (apply + numbers) (count numbers)))

(defn compose [f g]
  (fn [x] (f (g x))))

(defn repeated [f n]
  (if (= n 1)
    f
    (compose f (repeated f (- n 1)))))

(defn smooth [f]
  (fn [x] (average [(f (- x dx)) (f x), (f (+ x dx))])))

(defn nfold-smooth [f n]
  (repeated (smooth f) n))