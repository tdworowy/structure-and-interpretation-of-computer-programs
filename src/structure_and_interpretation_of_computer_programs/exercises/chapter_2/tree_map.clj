(defn square [x]
  (* x x))

(defn square-tree [tree f]
  (cond
    (number? tree) (f tree)
    (empty? tree) '()
    (sequential? tree) (map #(square-tree % f) tree)))

(println (square-tree (list 1 (list 2 (list 3 4) 5 (list 6 7))) square))