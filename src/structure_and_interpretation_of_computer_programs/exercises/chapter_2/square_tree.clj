(ns structure-and-interpretation-of-computer-programs.exercises.chapter-2.square-tree)

(defn square [x]
  (* x x))

(defn square-tree [tree]
  (cond
    (number? tree) (square tree)
    (empty? tree) '()
    (sequential? tree) (map square-tree tree)))

(println (square-tree (list 1 (list 2 (list 3 4) 5 (list 6 7)))))