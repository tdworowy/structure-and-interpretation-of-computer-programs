(ns structure-and-interpretation-of-computer-programs.abstraction-with-data.scale-tree)

(defn scale-tree-map1 [tree factor]
  (cond
    (number? tree) (* tree factor)
    (empty? tree) '()
    (sequential? tree) (map #(scale-tree-map1 % factor) tree)))

(defn scale-tree-map2 [tree factor]
  (map (fn [sub-tree]
         (if (sequential? sub-tree)
           (scale-tree-map2 sub-tree factor)
           (* sub-tree factor))) tree))

(defn scale-tree [tree factor]
  (cond
    (number? tree) (* tree factor)
    (empty? tree) '()
    (sequential? tree) (cons (scale-tree (first tree) factor) (scale-tree (rest tree) factor))))

(println (scale-tree-map1 (list 1 (list 2 (list 3 4) 5) (list 6 7)) 10))
(println (scale-tree-map2 (list 1 (list 2 (list 3 4) 5) (list 6 7)) 10))
(println (scale-tree (list 1 (list 2 (list 3 4) 5) (list 6 7)) 10))