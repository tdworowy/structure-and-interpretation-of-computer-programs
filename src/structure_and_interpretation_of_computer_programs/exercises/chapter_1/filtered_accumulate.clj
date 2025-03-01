(ns structure-and-interpretation-of-computer-programs.exercises.chapter-1.filtered_accumulate)

(defn filtered-accumulate [predicate? combiner null-value term a next b]
  (if (> a b) null-value
      (combiner
       (if (predicate? a) (term a) null-value)
       (filtered-accumulate predicate? combiner null-value term (next a) next b))))

(defn prime? [n]
  (if (<= n 1)
    false
    (not-any? #(zero? (mod n %)) (range 2 (inc (Math/sqrt n))))))

(defn int [n] (+ n 1))
(defn square [x]
  (* x x))

(defn sum-of-squares-prime [a b]
  (filtered-accumulate prime? + 0 square a inc b))

(println (sum-of-squares-prime 1 10))

(defn gcd [a b]
  (if (= b 0) a
      (gcd b (rem a b))))

(defn relative-prime? [i n]
  (= (gcd i n) 1))

(defn identity [x] x)

(defn product-of-relative-prime [n]
  (defn relative-prime? [i]
    (= (gcd i n) 1))
  (filtered-accumulate relative-prime? * 1 identity 1 inc (- n 1)))

(println (product-of-relative-prime 7))