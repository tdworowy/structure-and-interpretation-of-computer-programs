(ns structure-and-interpretation-of-computer-programs.abstraction-with-data.sets-as-trees)

(defn entry [tree] (first tree))
(defn left-branch [tree] (second  tree))
(defn right-branch [tree] (nth tree 2 nil))
(defn make-tree [entry left right] (list entry left right))

(defn element-of-set? [x set]
  (cond (empty? set) false
        (= x (entry set)) true
        (< x (entry set)) (element-of-set? x (left-branch set))
        (> x (entry set)) (element-of-set? x (right-branch set))))

(defn adjoin-set [x set]
  (cond (empty? set) (make-tree x () ())
        (= x (entry set)) set
        (< x  (entry set)) (make-tree (entry set) (adjoin-set x (left-branch set)) (right-branch set))
        (> x  (entry set)) (make-tree (entry set) (left-branch set) (adjoin-set x (right-branch set)))))

(defn tree->list-1 [tree]
  (if (empty? tree) '()
      (concat (tree->list-1 (left-branch tree)) (cons (entry tree) (tree->list-1 (right-branch tree))))))

(def sample-tree
  (make-tree
   1
   ()
   (make-tree
    2
    ()
    (make-tree
     3
     ()
     (make-tree
      4
      ()
      (make-tree
       5
       ()
       (make-tree
        6
        ()
        (make-tree
         7
         ()
         (make-tree
          9
          ()
          ())))))))))

(defn partial-tree [elements n]
  (if (= n 0)
    (cons '() elements)
    (let [left-size (int (/ (- n 1) 2))
          left-result (partial-tree elements left-size)
          left-tree (first left-result)
          non-left-elements (rest left-result)
          right-size (- n (+ left-size 1))
          this-entry (first non-left-elements)
          right-results (partial-tree (rest non-left-elements) right-size)
          right-tree (first right-results)
          remaining-elements (rest right-results)]
      (cons (make-tree this-entry left-tree right-tree) remaining-elements))))

(defn list->tree [elements]
  (first (partial-tree elements (count elements))))

(println (element-of-set? 2 sample-tree))
(println (element-of-set? 8 sample-tree))
(println (tree->list-1 sample-tree))
(println (tree->list-1 (adjoin-set 12 sample-tree)))
(println (tree->list-1 (adjoin-set 8 sample-tree)))

(println (list->tree [11 5 2 9 7 1]))
(println (list->tree [1 2 5 7 9 11]))