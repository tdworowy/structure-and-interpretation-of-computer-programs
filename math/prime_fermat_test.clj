(defn square [x]
  (* x x))

(defn expmod [base exp m]
  (cond
    (= exp 0) 1
    (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
    :else (rem (* base (expmod base (- exp 1) m)) m)))

(defn fermat-test [n]
  (defn try-it [a]
    (= (expmod a n n) a))
  (try-it (+ 1 (rand-int (- n 1)))))

(defn fast-prime? [n times]
  (cond
    (= times 0) true
    (fermat-test n) (fast-prime? n (- times 1))
    :else false))

(println (fast-prime? 97 2))
(println (fast-prime? 97 5))

(println (fast-prime? 224 2))
(println (fast-prime? 782438202 2))
(println (fast-prime? 782438202 5))
