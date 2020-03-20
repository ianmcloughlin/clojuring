(defn ++ [x] (+ x 1))
(defn -- [x] (- x 1))

(defn plus [x, y]
 (if (= y 0)
  x
  (+ 1 (plus x (-- y)))))

(print (plus 10 11))

(defn mult [x, y]
 (if (= y 1)
  x
  (+ x (mult x (-- y)))))

(print (mult 10 11))