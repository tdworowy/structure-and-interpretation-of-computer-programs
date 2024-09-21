;Square Roots via Newton's Method

(defn square [x]
  (* x x))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn good-enough? [guess x]
  (< (abs (- (square guess) x)) 0.001))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x) guess
      (sqrt-iter (improve guess x) x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

(println (sqrt 2))
(println (sqrt 9))
(println (sqrt 99))

(println (sqrt 814728764))
(println (sqrt 0.004))