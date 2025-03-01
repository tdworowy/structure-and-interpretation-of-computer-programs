(ns structure-and-interpretation-of-computer-programs.exercises.chapter-2.mobile)

(defn make-mobile [left right]
  (list left right))

(defn make-branch [length structure]
  (list length structure))

(defn left-branch [mobile]
  (first mobile))

(defn right-branch [mobile]
  (first (rest mobile)))

(defn branch-length [branch]
  (first branch))

(defn branch-structure [branch]
  (first (rest branch)))

(defn total-weight [mobile]
  (letfn [(branch-weight [branch]
            (let [structure (branch-structure branch)]
              (if (list? structure)
                (total-weight structure)
                structure)))]
    (let [left (left-branch mobile)
          right (right-branch mobile)]
      (+ (branch-weight left)
         (branch-weight right)))))

(defn torque [branch]
  (* (branch-length branch)
     (let [structure (branch-structure branch)]
       (if (list? structure)
         (total-weight structure)
         structure))))

(defn branch-balanced [left right]
  (= (torque left) (torque right)))

(defn balanced? [mobile]
  (branch-balanced (left-branch mobile) (right-branch mobile)))

(def m1
  (make-mobile (make-branch 2 2)
               (make-branch 2 2)))
(def m2
  (make-mobile (make-branch 3 4)
               (make-branch 3 m1)))

(def m3  (make-mobile (make-branch 4 3)
                      (make-branch 4 7)))

(println (total-weight m2))
(println (balanced? m2))
(println (balanced? m1))
(println (balanced? m3))