(ns structure-and-interpretation-of-computer-programs.exercises.chapter-2.interval_arithmetic2)

(defn make-interval [a b]
  (if
   (< a b) (list a b)
   (list b a)))

(defn make-center-width [c w]
  (make-interval (- c w) (+ c w)))

(defn lower-bound [x] (first x))
(defn upper-bound [x] (first (rest x)))

(defn center [i]
  (/ (+ (lower-bound i) (upper-bound i)) 2))

(defn width [i]
  (/ (- (upper-bound i) (lower-bound i)) 2))

(defn add-interval [x y] (make-interval (+ (lower-bound x) (lower-bound y)) (+ (upper-bound x) (upper-bound y))))
(defn sub-interval [x y] (make-interval (- (lower-bound x) (upper-bound y)) (- (upper-bound x) (lower-bound y))))

(defn mul-interval [x y]
  (let [p1 (* (lower-bound x) (lower-bound y))
        p2 (* (lower-bound x) (upper-bound y))
        p3 (* (upper-bound x) (lower-bound y))
        p4 (* (upper-bound x) (upper-bound y))]
    (make-interval (min p1 p2 p3 p4) (max p1 p2 p3 p4))))

(defn div-interval [x y] (if (= (lower-bound y) (upper-bound y)) (println "Division by zeo")
                             (mul-interval x (make-interval (/ 1.0 (upper-bound y)) (/ 1.0 (lower-bound y))))))

(defn with-interval [x] (/ (- (upper-bound x) (lower-bound x)) 2.0))

(defn make-center-percent [c p]
  (make-center-width c (* c p 0.01)))

(defn percent [i]
  (* 100.0 (/ (width i) (center i))))

(println (width (make-interval 100 101)))
(println (center (make-interval 100 101)))
(println (make-interval 1 10))

(println (make-center-width (width (make-interval 1 10)) (center (make-interval 1 10))))

(println (percent (make-interval 1.0 2.0)))
(println (center (make-interval 1.0 2.0)))

(println (percent (make-interval 2.0 5.0)))
(println (center (make-interval 2.0 5.0)))
(println (make-center-percent (center (make-interval 2.0 5.0)) (percent (make-interval 2.0 5.0))))

(println "__________________________________")

(defn par1 [r1 r2]
  (div-interval (mul-interval r1 r2) (add-interval r1 r1)))

(defn par2 [r1 r2]
  (let [one (make-interval 1 1)]
    (div-interval one (add-interval (div-interval one r1) (div-interval one r2)))))

(println (par1 (make-interval 2.0 5.0) (make-interval 3.0 4.0)))
(println (par2 (make-interval 2.0 5.0) (make-interval 3.0 4.0)))

(println "__________________________________")
(println (div-interval (make-interval 2 5) (make-interval 2 5)))
(println (div-interval (make-interval 1 1) (make-interval 2 5)))