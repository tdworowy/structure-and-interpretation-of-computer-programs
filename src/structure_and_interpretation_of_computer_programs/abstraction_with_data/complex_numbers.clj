(ns structure-and-interpretation-of-computer-programs.abstraction-with-data.complex-numbers
  (:require [clojure.math :as math]))

(defn square [x] (* x x))

; tags
(defn attache-tag [type-tag contents]
  (cons type-tag contents))

(defn type-tag [datum]
  (if (seq? datum)
    (first datum)
    (println "Error Bad tagged datum -- TYPE-TAG" datum)))

(defn contents [datum]
  (if (seq? datum)
    (second datum)
    (println "Error Bad tagged datum -- CONTENTS" datum)))

(defn rectangular? [z]
  (= (type-tag z) 'rectangular))

(defn polar? [z]
  (= (type-tag z) 'polar))

; first representation
(defn make-from-real-img-rectangular [x y] (cons x y))
(defn make-from-mag-ang-rectangular [r a] (cons (* r (math/cos a)) (* r (math/sin a))))

(defn real-part-rectangular [z] (first z))
(defn img-part-rectangular [z] (second z))

(defn magnitude-rectangular [z] (math/sqrt (+ (square (real-part-rectangular z)) (square (img-part-rectangular z)))))
(defn angle-rectangular [z] (math/atan2 (img-part-rectangular z) (real-part-rectangular z)))

; second representation
(defn make-from-real-img-polar [x y] (cons (math/sqrt (+ (square x) (square y))) (math/atan2 y x)))
(defn make-from-mag-ang-polar [r a] (cons r a))

(defn magnitude-polar [z] (first z))
(defn angle-polar [z] (second z))

(defn real-part-polar [z] (* (magnitude-polar z) (math/cos (angle-polar z))))
(defn img-part-polar [z] (* (magnitude-polar z) (math/sin (angle-polar z))))

; generic
(defn real-part [z]
  (cond (rectangular? z) (real-part-rectangular (contents z))
        (polar? z) (real-part-polar (contents z))
        :else (println "Error: Unknowns type -- REAL-PART" z)))

(defn img-part [z]
  (cond (rectangular? z) (img-part-rectangular (contents z))
        (polar? z) (img-part-polar (contents z))
        :else (println "Error: Unknowns type -- IMAG-PART" z)))

(defn magnitude [z]
  (cond (rectangular? z) (magnitude-rectangular (contents z))
        (polar? z) (magnitude-polar (contents z))
        :else (println "Error: Unknowns type -- MAGNITUDE" z)))

(defn angle [z]
  (cond (rectangular? z) (angle-rectangular (contents z))
        (polar? z) (angle-polar (contents z))
        :else (println "Error: Unknowns type -- ANGLE" z)))

(defn make-from-real-img [x y]
  (make-from-real-img-rectangular x y))

(defn make-from-mag-ang [x y]
  (make-from-mag-ang-polar x y))

; operations
(defn add-complex [z1 z2]
  (make-from-real-img (+ (real-part z1) (real-part z2)) (+ (img-part z1) (img-part z2))))

(defn sub-complex [z1 z2]
  (make-from-real-img (- (real-part z1) (real-part z2)) (- (img-part z1) (img-part z2))))

(defn mul-complex [z1 z2]
  (make-from-mag-ang (* (magnitude z1) (magnitude z2)) (+ (angle z1) (angle z2))))

(defn div-complex [z1 z2]
  (make-from-mag-ang (/ (magnitude z1) (magnitude z2)) (- (angle z1) (angle z2))))

; message passing
(defn make-from-real-img [x y]
  (fn [op]
    (cond
      (= op 'real-part) x
      (= op 'image-part) y
      (= op 'magnitude) (math/sqrt (+ (square x) (square y)))
      (= op 'angle) (math/atan2 x y)
      :else (println "Unknown op" op))))

(defn make-from-mag-ang [r a]
  (fn [op]
    (cond
      (= op 'real-part) (* r (math/cos a))
      (= op 'image-part) (* r (math/sin a))
      (= op 'magnitude) r
      (= op 'angle) a
      :else (println "Unknown op" op))))

(defn apply-generic [op arg] (arg op))

(println (apply-generic 'real-part (make-from-real-img 2 2)))
(println (apply-generic 'image-part (make-from-real-img 2 2)))
(println (apply-generic 'magnitude (make-from-real-img 2 2)))
(println (apply-generic 'angle (make-from-real-img 2 2)))

(println (apply-generic 'real-part (make-from-mag-ang 2 2)))
(println (apply-generic 'image-part (make-from-mag-ang 2 2)))
(println (apply-generic 'magnitude (make-from-mag-ang 2 2)))
(println (apply-generic 'angle (make-from-mag-ang 2 2)))