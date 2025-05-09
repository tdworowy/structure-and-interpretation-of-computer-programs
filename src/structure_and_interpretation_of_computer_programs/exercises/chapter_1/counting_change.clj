(ns structure-and-interpretation-of-computer-programs.exercises.chapter-1.counting-change)

(defn first-denomination [kinds-of-coins]
  (cond
    (= kinds-of-coins 1) 1
    (= kinds-of-coins 2) 5
    (= kinds-of-coins 3) 10
    (= kinds-of-coins 4) 25
    (= kinds-of-coins 5) 50))

(defn cc [amount kinds-of-coins]
  (cond
    (= amount 0) 1
    (or (< amount 0) (= kinds-of-coins 0)) 0
    :else (+ (cc amount (- kinds-of-coins 1)) (cc (- amount (first-denomination kinds-of-coins)) kinds-of-coins))))

(defn count-changes [amount]
  (cc amount 5))

(println (count-changes 100))
(println (count-changes 0))
(println (count-changes -1))
(println (count-changes 1))