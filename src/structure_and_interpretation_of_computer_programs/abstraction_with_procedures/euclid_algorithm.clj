(ns structure-and-interpretation-of-computer-programs.abstraction-with-procedures.euclid-algorithm)

(defn gcd [a b]
  (if (= b 0) a
      (gcd b (rem a b))))

(println (gcd 206 40))