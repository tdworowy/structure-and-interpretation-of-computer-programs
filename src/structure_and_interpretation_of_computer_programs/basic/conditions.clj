(ns structure-and-interpretation-of-computer-programs.basic.conditions)

(defn _abs [x]
  (cond
    (> x 0) x
    (= x 0) 0
    (< x 0) (- x)))

(println (_abs 1))
(println (_abs 0))
(println (_abs -1))

(defn _abs2 [x]
  (if
   (< x 0)
    (- x)
    x))

(println (_abs2 1))
(println (_abs2 0))
(println (_abs2 -1))

(defn >= [x y]
  (or (> x y) (= x y)))

(println (>= 2 3))