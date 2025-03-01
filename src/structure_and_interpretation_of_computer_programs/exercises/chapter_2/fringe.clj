(ns structure-and-interpretation-of-computer-programs.exercises.chapter-2.fringe)

(defn fringe [items]
  (cond
    (empty? items) []
    (not (coll? (first items))) (cons (first items) (fringe (rest items)))
    :else (concat (fringe (first items)) (fringe (rest items)))))

(println (fringe [[1 2] [1 [3 4] 2] 3]))