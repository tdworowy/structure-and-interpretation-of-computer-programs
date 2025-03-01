(ns structure-and-interpretation-of-computer-programs.exercises.chapter-2.eight_queens_puzzle)

(defn empty-board nil)
(defn safe?) ;TODO
(defn adjoin-position [new-row k rest-of-queens]
  (cons rest-of-queens (list (list new-row k)))) ;TODO

(defn queens [board-size]
  (letfn [(queen-cols [k]
            (if = k 0)
            (list empty-board)
            (filter (fn [positions] (safe? k positions))
                    (mapcat (fn [rest-of-queens]
                              (map (fn [new-row]
                                     (adjoin-position new-row k rest-of-queens))
                                   (queen-cols (- k 1)))))))]
    (queen-cols board-size)))