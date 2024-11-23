(defn compose [f g]
  (fn [x] (f (g x))))

(defn repeated [f n]
  (if (= n 1)
    f
    (compose f (repeated f (- n 1)))))

(defn square [x]
  (* x x))

(println ((repeated square 2) 5))