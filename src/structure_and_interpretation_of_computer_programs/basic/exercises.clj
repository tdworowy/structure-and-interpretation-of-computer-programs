(ns structure-and-interpretation-of-computer-programs.basic.exercises)
; define a procedure that takes three number as arguments and returns sum of the squares of two larger numbers

(defn ex1 [x y z]
  (cond
    (and (> x y) (> y z)) (+ (* x x) (* y y))
    (and (> x y) (> z y)) (+ (* x x) (* z z))
    (and (> y x) (> z x)) (+ (* y y) (* z z))))

(println (ex1 3 2 1))
(println (ex1 1 2 3))
(println (ex1 3 1 2))

(defn a-plus-abs-b [a b]
  ((if (> b 0) + -) a b))

(println (a-plus-abs-b 1 1))
(println (a-plus-abs-b 1 0))
(println (a-plus-abs-b 1 -1))
(println (a-plus-abs-b -1 -1)
         (println (a-plus-abs-b -1 1)))