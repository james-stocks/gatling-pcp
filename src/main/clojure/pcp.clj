(ns pcp
  (:gen-class :methods [#^{:static true} [associate_request [String] bytes]
                        #^{:static true} [new_message [String] bytes]])
  (require [puppetlabs.pcp.message :as message]))

(defn -associate_request
  [uri]
  (-> (message/make-message :sender uri
                            :targets ["pcp:///server"]
                            :message_type "http://puppetlabs.com/associate_request")
      (message/set-expiry 3 :seconds)
      (message/encode)))

(defn -new_message
  [uri]
  (-> (message/make-message :sender uri
                            :targets [uri]
                            :message_type "hello")
      (message/set-expiry 3 :seconds)
      (message/encode)))
