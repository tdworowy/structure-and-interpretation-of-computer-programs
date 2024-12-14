(defn count-leaves [x]
  (cond
    (not (coll? x)) 1
    (empty? x) 0
    :else (+ (count-leaves (first x)) (count-leaves (rest (seq x))))))

(println (count-leaves [1 2 3]))
(println (count-leaves [[1 2 3] [1 23 3]]))
(println (count-leaves [[1 2 3] [1 23 3] 2]))
