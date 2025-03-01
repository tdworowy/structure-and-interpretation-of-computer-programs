(ns structure-and-interpretation-of-computer-programs.exercises.chapter-2.same-parity)

(defn same-parity [x & z]
  (let [parity (if (even? x) even? odd?)]
    (filter parity z)))

(println (same-parity 1 2 3 4 5))
(println (same-parity 2 4 7 6))