

(defn sum [term a next b]
  (if (> a b) 0
      (+ (term a) (sum term (next a) next b)))) ;linear recursion

(defn sum-tail [term a next b]
  (defn iter [a result]
    (if (> a b)
      result
      (iter (next a) (+ result (term a)))))
  (iter a 0))      ;tail recursion

(defn integral [f a b dx]
  (defn add-dx [x] (+ x dx))
  (* (sum f (+ a (/ dx 2.0)) add-dx b) dx))

;h/3(y0+4y1+2y2+4y3+2y4+⋯+2yn−2+4yn−1+yn)
;h=(b−a)/n
;yk=f(a+kh)
(defn integral-simpson [f a b n]
  (def h (/ (- b a) n))
  (defn add-2h [x] (+ x h h))
  (* (+ (f a)
        (* 2 (sum-tail f (add-2h a) add-2h (- b h)))
        (* 4 (sum-tail f (+ a h) add-2h b))
        (f b))
     (/ h 3)))

(defn cube [x] (* x x x))

(println (integral cube 0 1 0.01))
(println (integral cube 0 1 0.001))

(println (integral-simpson cube 0 1.0 100))
(println (integral-simpson cube 0 1.0 1000))