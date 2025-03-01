(ns structure-and-interpretation-of-computer-programs.exercises.chapter-2.interval_arithmetic1)

(defn make-interval [a b]
  (if
   (< a b) (list a b)
   (list b a)))

(defn lower-bound [x] (first x))
(defn upper-bound [x] (first (rest x)))

(defn add-interval [x y] (make-interval (+ (lower-bound x) (lower-bound y)) (+ (upper-bound x) (upper-bound y))))
(defn sub-interval [x y] (make-interval (- (lower-bound x) (upper-bound y)) (- (upper-bound x) (lower-bound y))))

(defn mul-interval [x y]
  (let [p1 (* (lower-bound x) (lower-bound y))
        p2 (* (lower-bound x) (upper-bound y))
        p3 (* (upper-bound x) (lower-bound y))
        p4 (* (upper-bound x) (upper-bound y))]
    (make-interval (min p1 p2 p3 p4) (max p1 p2 p3 p4))))

(defn div-interval_old [x y] (mul-interval x (make-interval (/ 1.0 (upper-bound y)) (/ 1.0 (lower-bound y)))))
(defn div-interval [x y] (if (= (lower-bound y) (upper-bound y)) (println "Division by zeo")
                             (mul-interval x (make-interval (/ 1.0 (upper-bound y)) (/ 1.0 (lower-bound y))))))

(defn with-interval [x] (/ (- (upper-bound x) (lower-bound x)) 2.0))

(println (add-interval (make-interval 1 2) (make-interval 2 3)))
(println (mul-interval (make-interval 1 2) (make-interval 2 3)))
(println (div-interval (make-interval 1 2) (make-interval 2 3)))
(println (sub-interval (make-interval 5 10) (make-interval 1 3)))
(println (with-interval (make-interval 5 10)))
(println (upper-bound (make-interval 5 10)))
(println (lower-bound (make-interval 5 10)))

(println (with-interval (make-interval 100 101)))

(println (div-interval_old (make-interval 2 5) (make-interval 2 2)))
(println (div-interval (make-interval 2 5) (make-interval 2 2)))