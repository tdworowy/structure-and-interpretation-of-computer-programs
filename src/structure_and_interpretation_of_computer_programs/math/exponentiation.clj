(defn fast-expt [b, n]
  (cond
    (= n 0) 1
    (even? n) (Math/pow (fast-expt b (/ n 2)) 2)
    :else (* b (fast-expt b (- n 1)))))

(defn fast-expt-iter [a b n]
  (cond (= n 0)
        a
        (even? n)
        (fast-expt-iter a (* b b) (/ n 2))
        :else
        (fast-expt-iter (* a b) b (- n 1))))

(println (fast-expt 9 3))
(println (fast-expt-iter 1 9 3))
(println (Math/pow 9 3))