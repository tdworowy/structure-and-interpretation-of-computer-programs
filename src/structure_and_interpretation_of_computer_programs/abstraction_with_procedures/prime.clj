(ns structure-and-interpretation-of-computer-programs.abstraction-with-procedures.prime)

(defn square [x]
  (* x x))

(defn divides? [a b]
  (= (rem b a) 0))

(defn next [n]
  (if (= n 2) 3
      (+ n 2)))

(defn find-divisor [n test-divisor]
  (cond
    (> (square test-divisor) n) n
    (divides? test-divisor n) test-divisor
    :else (find-divisor n (next test-divisor))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn -main []
  (println (prime? 2))
  (println (prime? 4))
  (println (prime? 97))
  (println (prime? 100000081)))