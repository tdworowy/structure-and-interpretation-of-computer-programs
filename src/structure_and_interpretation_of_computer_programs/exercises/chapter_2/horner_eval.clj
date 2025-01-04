(defn horner-eval [x coefficient-sequence]
  (reduce (fn [this-coeff higher-terms] (+ higher-terms (* this-coeff x))) 0 (reverse coefficient-sequence)))

(println (horner-eval 2 [1 3 0 5 0 1]))
;1 + 3x + 5x^3 + x^5 at x=2 should be 79

(println (horner-eval 2 [1 2 3]))
;3x^2 + 2x + 1 at x=2 should be 17


