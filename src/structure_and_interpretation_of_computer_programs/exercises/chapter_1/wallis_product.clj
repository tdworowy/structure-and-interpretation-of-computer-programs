(ns structure-and-interpretation-of-computer-programs.exercises.chapter-1.wallis-product)

(defn product [term a next b]
  (if (> a b) 1
      (* (term a) (product term (next a) next b)))) ;linear recursion

(defn product-tail [term a next b]
  (letfn [(iter [a result]
            (if (> a b)
              result
              (iter (next a) (* result (term a)))))]
    (iter a 1)))      ;tail recursion

(defn identity [x] x)
(defn inc [n] (+ n 1))
(defn factorial [n] (product-tail identity 1 inc n))

(defn wallis-product [n]
  (letfn [(term [n]
            (* (/ (* 2 n)
                  (- (* 2 n) 1))
               (/ (* 2 n)
                  (+ (* 2 n) 1))))]
    (product-tail term 1.0 inc n)))

(println (* 2 (wallis-product 5000)))