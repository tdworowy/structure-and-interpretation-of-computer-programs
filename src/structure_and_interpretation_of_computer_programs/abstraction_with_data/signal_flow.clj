(ns structure-and-interpretation-of-computer-programs.abstraction-with-data.signal-flow)

(defn enumerate-interval [low high]
  (if (> low high) nil
      (cons low (enumerate-interval (+ low 1) high))))

(defn enumerate-tree [tree]
  (cond
    (not (coll? tree)) (list tree)
    (empty? tree) nil
    :else (concat (enumerate-tree (first tree)) (enumerate-tree (rest tree)))))

(println (enumerate-interval 2 7))
(println (enumerate-tree (list 1 (list 2 (list 3 4)) 5)))

(defn square [x]
  (* x x))

(defn sum-odd-squares [tree]
  (reduce + 0 (map square (filter odd? (enumerate-tree tree)))))

(println (sum-odd-squares  (list 1 (list 2 (list 3 4)) 5)))

(defn fib-iter [a b count]
  (if (= count 0)
    b
    (fib-iter (+ a b) a (- count 1))))

(defn fib [n]
  (fib-iter 1 0 n))

(defn even-fibs1 [n]
  (filter even? (map fib (enumerate-interval 0 n))))

(defn even-fibs2 [n]
  (filter even? (map fib (range (inc n)))))

(println (even-fibs1 10))
(println (even-fibs2 10))

(defn list-fib-squares [n]
  (map square (map fib (range (inc n)))))

(println (list-fib-squares 10))

(defn product-of-squares-of-odd-elements [sequence]
  (reduce * 1 (map square (filter odd? sequence))))

(println (product-of-squares-of-odd-elements [1 2 3 4 5]))
