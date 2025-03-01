(ns structure-and-interpretation-of-computer-programs.abstraction-with-procedures.fibonacci2)

(defn fib-iter [a b p q count]
  (cond
    (= count 0) b
    (even? count)
    (fib-iter a b (+ (* p p) (* q q)) (+ (* 2 q p) (* q q)) (/ count 2))
    :else
    (fib-iter (+ (* b q) (* a q) (* a p))
              (+ (* b p) (* a q)) p q (- count 1))))

(defn fib [n]
  (fib-iter 1 0 0 1 n))

(println (fib 4))
(println (fib 12))
(println (fib 53))
(println (fib 91))