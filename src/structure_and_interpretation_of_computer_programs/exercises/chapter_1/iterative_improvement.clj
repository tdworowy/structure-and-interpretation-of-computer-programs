(ns structure-and-interpretation-of-computer-programs.exercises.chapter-1.iterative-improvement)

(defn iterative-improvement [good-enough? improve]
  (letfn [(iter [guess]
            (if (good-enough? guess)
              guess
              (recur (improve guess))))]
    (fn [guess] (iter guess))))

(defn average [numbers] (/ (apply + numbers) (count numbers)))
(defn square [x] (* x x))

(defn improve-sqrt [x]
  (fn [guess] (average [guess (/ x guess)])))

(defn good-enough-sqrt? [x]
  (fn [guess] (< (abs (- (square guess) x)) 0.001)))

(defn sqrt [x]
  ((iterative-improvement (good-enough-sqrt? x) (improve-sqrt x)) 1.0))

(println (sqrt 4))



