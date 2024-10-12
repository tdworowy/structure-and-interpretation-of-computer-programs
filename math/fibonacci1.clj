(defn fib-iter [a b count]
  (if (= count 0)
    b
    (fib-iter (+ a b) a (- count 1))))

(defn fib [n]
  (fib-iter 1 0 n))

(println (fib 4))
(println (fib 12))
(println (fib 53))
(println (fib 91))