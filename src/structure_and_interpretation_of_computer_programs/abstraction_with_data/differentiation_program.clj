(require '[clojure.math :as math])

(defn variable? [x] (symbol? x))
(defn same-variable? [v1 v2] (and (variable? v1) (variable? v2) (= v1 v2)))

(defn make-sum [a1 a2]
  (cond (and (number? a1) (= a1 0)) a2
        (and (number? a2) (= a2 0)) a1
        (and (number? a1) (number? a2)) (+ a1 a2)
        :else (list '+ a1 a2)))

(defn sum? [x] (and (coll? x) (= (first x) '+)))
(defn addend [e] (nth e 1))
(defn augend [e] (nth e 2))

(defn make-product [m1 m2]
  (cond (or (and (number? m1) (= m1 0)) (and (number? m2) (= m2 0))) 0
        (and (number? m1) (= m1 1)) m2
        (and (number? m2) (= m2 1)) m1
        (and (number? m1) (number? m2)) (* m1 m2)
        :else (list '* m1 m2)))

(defn product? [x] (and (coll? x) (= (first x) '*)))
(defn multiplier [e] (nth e 1))
(defn multiplicand [e] (nth e 2))

(defn make-exponentiation [b e]
  (cond (and (number? e) (= e 0)) 1
        (and (number? e) (= e 1)) b
        (and (number? e) (number? b)) (math/pow b e)
        :else (list '** b e)))

(defn exponentiation? [x] (and (coll? x) (= (first x) '**)))
(defn base [e] (nth e 1))
(defn exponent [e] (nth e 2))

(defn deriv [exp var]
  (cond (number? exp) 0
        (variable? exp)
        (if (same-variable? exp var) 1 0)
        (sum? exp) (make-sum (deriv (addend exp) var)
                             (deriv (augend exp) var))
        (product? exp) (make-sum (make-product (multiplier exp)
                                               (deriv (multiplicand exp) var))
                                 (make-product (deriv (multiplier exp) var) (multiplicand exp)))
        (exponentiation? exp) (make-product (exponent exp) (make-product (make-exponentiation (base exp) (- (exponent exp) 1))
                                                                         (deriv (base exp) var)))
        :else (println "Unknown expression type")))

(println (deriv '(+ x 3) 'x))
(println (deriv '(* x y) 'x))
(println (deriv '(* (* x y) (+ x 3)) 'x))
(println (deriv '(** x 3) 'x))
(println (deriv '(** x 0) 'x))
(println (deriv '(** x 1) 'x))
(println (deriv '(+ (** x 2) (** x 3)) 'x))