(ns structure-and-interpretation-of-computer-programs.abstraction-with-procedures.cube-root)

;Cube Roots via Newton's Method
; (x / square(y) + 2 * y) / 3

(defn square [x]
  (* x x))

(defn cube [x]
  (* x x x))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (/ (+ (/ x (square guess)) (* 2 guess)) 3))

(defn good-enough? [guess x]
  (< (abs (- (cube guess) x)) 0.001))

(defn cubert-iter [guess x]
  (if (good-enough? guess x) guess
      (cubert-iter (improve guess x) x)))

(defn cubert [x]
  (cubert-iter 1.0 x))

(println (cubert 9))
(println (cubert 27))
