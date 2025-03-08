(ns structure-and-interpretation-of-computer-programs.abstraction-with-data.sets)

(defn element-of-set? [x set]
  (cond (empty? set) false
        (= x (first set)) true
        :else (element-of-set? x (rest set))))

(defn adjoin-set [x set]
  (if (element-of-set? x set) set)
  (cons x set))

(defn intersection-set [set1 set2]
  (cond (or (empty? set1) (empty? set2)) '()
        (element-of-set? (first set1) set2) (cons (first set1) (intersection-set (rest set1) set2))
        :else (intersection-set (rest set1) set2)))

(defn union-set [set1 set2]
  (cond (and (empty? set1) (empty? set2)) '()
        (and (empty? set1) (not (empty? set2))) set2
        (and (not (empty? set1)) (empty? set2)) set1
        (not (element-of-set? (first set1) set2)) (cons (first set1) (union-set (rest set1) set2))
        :else (union-set (rest set1) set2)))

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