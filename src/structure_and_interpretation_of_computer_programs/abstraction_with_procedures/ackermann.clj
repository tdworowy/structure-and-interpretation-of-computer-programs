(defn A0 [x y]
  (cond
    (= y 0) 0
    (= x 0) (* 2 y)
    (= y 1) 2
    :else (A0 (- x 1) (A0 x (- y 1))))) ; from book

(defn A [x y]
  (cond
    (= x 0) (+ y 1)
    (= y 0) (A (- x 1) 1)
    :else (A (- x 1) (A x (- y 1)))))

(println (A 1 10)) ; 12
(println (A 2 4)) ; 11
(println (A 3 3)) ; 61

(println (A 0 0)) ; 1
(println (A 1 0)) ; 2
(println (A 0 1)) ; 2

(println (A0 0 1))
(println (A0 1 1))
(println (A0 2 1))

(println (A0 0 2))
(println (A0 1 2))
(println (A0 2 2))

(println (A0 0 3))
(println (A0 1 3))
(println (A0 2 3))

(println (A0 0 4))
(println (A0 1 4))
(println (A0 2 4))
;(println (A0 3 4))

(println (A 4 1))
(println (A 4 2))