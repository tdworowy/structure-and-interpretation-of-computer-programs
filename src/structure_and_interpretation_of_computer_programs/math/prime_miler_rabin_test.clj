;https://sicp-solutions.net/post/sicp-solution-exercise-1-28/
(defn square [x]
  (* x x))

(defn remainder-square-checked [x m]
  (if (and (not (or (= x 1)
                    (= x (- m 1))))
           (= (rem (* x x) m) 1))
    0
    (rem (* x x) m)))

(defn expmod-checked [base exp m]
  (cond
    (= exp 0) 1
    (even? exp) (remainder-square-checked (expmod-checked base (/ exp 2) m) m)
    :else (rem (* base (expmod-checked base (- exp 1) m)) m)))

(defn miler-rabin-test [n]
  (defn try-it [a]
    (= (expmod-checked a (- n 1) n) 1))
  (try-it (+ 1 (rand-int (- n 1)))))

(defn miller-rabin-prime? [n times]
  (cond
    (= times 0) true
    (miler-rabin-test n) (miller-rabin-prime? n (- times 1))
    :else false))

(println (miller-rabin-prime? 2 10))
(println (miller-rabin-prime? 1009 10))
(println (miller-rabin-prime? 97 10))
(println (miller-rabin-prime? 97 10))
(println (miller-rabin-prime? 99 10))

(println (miller-rabin-prime? 224 10))