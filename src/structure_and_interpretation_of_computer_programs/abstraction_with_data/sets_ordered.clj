(ns structure-and-interpretation-of-computer-programs.abstraction-with-data.sets-ordered)

(defn element-of-set? [x set]
  (cond (empty? set) false
        (= x (first set)) true
        (< x (first set)) false
        :else (element-of-set? x (rest set))))

(defn adjoin-set [x set]
  (cond (empty? set) (list x)
        (= x (first set)) set
        (< x (first set)) (cons x set)
        :else (cons (first set) (adjoin-set x (rest set)))))

(defn intersection-set [set1 set2]
  (if (or (empty? set1) (empty? set2)) '()
      (let [x1 (first set1) x2 (first set2)]
        (cond (= x1 x2) (cons x1 (intersection-set (rest set1) (rest set2)))
              (< x1 x2) (intersection-set (rest set1) set2)
              (< x2 x1) (intersection-set set1 (rest set2))))))

(defn union-set [set1 set2]
  (letfn [(union [s1 s2 acc]
            (cond
              (and (empty? s1) (empty? s2)) acc
              (empty? s1) (into acc s2)
              (empty? s2) (into acc s1)
              :else (let [x1 (first s1)]
                      (if (some #(= x1 %) s2)
                        (union (rest s1) s2 acc)
                        (union (rest s1) s2 (conj acc x1))))))]
    (sort (union set1 set2 []))))

(println (element-of-set? 1 [1 2 3]))
(println (adjoin-set 4 [1 2 3]))
(println (intersection-set [1 2 4] [1 2 3]))
(println (intersection-set [] [1 2 3]))
(println (intersection-set [1 2 3] []))
(println (intersection-set [] []))
(println (union-set [1 2 4 5] [1 2 3 6]))
(println (union-set [] [1 2 3 6]))
(println (union-set [1 2 4 5] []))
(println (union-set [] []))