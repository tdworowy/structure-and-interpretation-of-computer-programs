(load-file "../math/fixed-point.clj")

(defn cubic [a b c]
  (fn [x] (+ (* x x x) (* a x x) (* b x) c)))

(println (newton-method (cubic 1 1 1) 1))