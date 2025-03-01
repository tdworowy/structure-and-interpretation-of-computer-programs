(ns structure-and-interpretation-of-computer-programs.abstraction-with-data.memq)

(defn memq [item x]
  (cond
    (empty? x) false
    (= item (first x)) x
    :else (memq item (rest x))))

(println (memq 'apple '(pear banana prune)))
(println (memq 'apple '(x (apple sauce) y apple pear)))
