(ns segment.module2
  (:require [segment.module1 :as module1]
            [segment.module0 :as module0]))

(defrecord My-record []
  module0/Proto
  (module0/method-1 [_ignoreme] :my-result))

(defn make-my-record [] (My-record.))