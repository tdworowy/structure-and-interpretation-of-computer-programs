(ns structure-and-interpretation-of-computer-programs.abstraction-with-procedures.square-root2)

;Square Roots via Newton's Method

(defn square [x]
  (* x x))

(defn average [x y]
  (/ (+ x y) 2))

(defn sqrt [x]
  (letfn [(good-enough? [guess]
            (< (abs (- (square guess) x)) 0.001))]
    (letfn [(improve [guess]
              (average guess (/ x guess)))]

      (letfn [(sqrt-iter [guess]
                (if (good-enough? guess) guess
                    (sqrt-iter (improve guess))))]
        (sqrt-iter 1.0)))))

(println (sqrt 2))
(println (sqrt 9))
(println (sqrt 99))

(println (sqrt 814728764))
(println (sqrt 0.004))