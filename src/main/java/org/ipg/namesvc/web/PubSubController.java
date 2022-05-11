package org.ipg.namesvc.web;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class PubSubController {

//    private static final Logger LOGGER = LoggerFactory.getLogger(PubSubController.class);
//
//    private final PubSubTemplate pubSubTemplate;
//
//    private final PubSubAdmin pubSubAdmin;
//
//    private final ArrayList<Subscriber> allSubscribers;
//
//    public PubSubController(PubSubTemplate pubSubTemplate, PubSubAdmin pubSubAdmin) {
//        this.pubSubTemplate = pubSubTemplate;
//        this.pubSubAdmin = pubSubAdmin;
//        this.allSubscribers = new ArrayList<>();
//    }
//
//    @GetMapping("/topics")
//    public List<String> getTopics() {
//        return this.pubSubAdmin.listTopics()
//                .stream()
//                .map(Topic::getName)
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping("/subscriptions")
//    public List<String> getSubscriptions() {
//        return this.pubSubAdmin.listSubscriptions()
//                .stream()
//                .map( subscription -> subscription.getName() + ":" + subscription.getTopic())
//                .collect(Collectors.toList());
//    }
//
//    @PostMapping("/createTopic")
//    public RedirectView createTopic(@RequestParam("topicName") String topicName) {
//        this.pubSubAdmin.createTopic(topicName);
//
//        return buildStatusView("Topic creation successful.");
//    }
//
//    @PostMapping("/createSubscription")
//    public RedirectView createSubscription(
//            @RequestParam("topicName") String topicName,
//            @RequestParam("subscriptionName") String subscriptionName) {
//        this.pubSubAdmin.createSubscription(subscriptionName, topicName);
//
//        return buildStatusView("Subscription creation successful.");
//    }
//
//    @GetMapping("/postMessage")
//    public RedirectView publish(
//            @RequestParam("topicName") String topicName,
//            @RequestParam("message") String message,
//            @RequestParam("count") int messageCount) {
//        for (int i = 0; i < messageCount; i++) {
//            this.pubSubTemplate.publish(topicName, message);
//        }
//
//        return buildStatusView("Messages published asynchronously; status unknown.");
//    }
//
//    @GetMapping("/pull")
//    public RedirectView pull(@RequestParam("subscription1") String subscriptionName) {
//
//        Collection<AcknowledgeablePubsubMessage> messages =
//                this.pubSubTemplate.pull(subscriptionName, 10, true);
//
//        if (messages.isEmpty()) {
//            return buildStatusView("No messages available for retrieval.");
//        }
//
//        RedirectView returnView;
//        try {
//            ListenableFuture<Void> ackFuture = this.pubSubTemplate.ack(messages);
//            ackFuture.get();
//            returnView =
//                    buildStatusView(String.format("Pulled and acked %s message(s)", messages.size()));
//        } catch (Exception ex) {
//            LOGGER.warn("Acking failed.", ex);
//            returnView = buildStatusView("Acking failed");
//        }
//
//        return returnView;
//    }
//
//    @GetMapping("/multipull")
//    public RedirectView multipull(
//            @RequestParam("subscription1") String subscriptionName1,
//            @RequestParam("subscription2") String subscriptionName2) {
//
//        Set<AcknowledgeablePubsubMessage> mixedSubscriptionMessages = new HashSet<>();
//        mixedSubscriptionMessages.addAll(this.pubSubTemplate.pull(subscriptionName1, 1000, true));
//        mixedSubscriptionMessages.addAll(this.pubSubTemplate.pull(subscriptionName2, 1000, true));
//
//        if (mixedSubscriptionMessages.isEmpty()) {
//            return buildStatusView("No messages available for retrieval.");
//        }
//
//        RedirectView returnView;
//        try {
//            ListenableFuture<Void> ackFuture = this.pubSubTemplate.ack(mixedSubscriptionMessages);
//            ackFuture.get();
//            returnView =
//                    buildStatusView(
//                            String.format("Pulled and acked %s message(s)", mixedSubscriptionMessages.size()));
//        } catch (Exception ex) {
//            LOGGER.warn("Acking failed.", ex);
//            returnView = buildStatusView("Acking failed");
//        }
//
//        return returnView;
//    }
//
//    @GetMapping("/subscribe")
//    public RedirectView subscribe(@RequestParam("subscription") String subscriptionName) {
//        Subscriber subscriber =
//                this.pubSubTemplate.subscribe(
//                        subscriptionName,
//                        message -> {
//                            LOGGER.info(
//                                    "Message received from "
//                                            + subscriptionName
//                                            + " subscription: "
//                                            + message.getPubsubMessage().getData().toStringUtf8());
//                            message.ack();
//                        });
//
//        this.allSubscribers.add(subscriber);
//        return buildStatusView("Subscribed.");
//    }
//
//    @PostMapping("/deleteTopic")
//    public RedirectView deleteTopic(@RequestParam("topic") String topicName) {
//        this.pubSubAdmin.deleteTopic(topicName);
//
//        return buildStatusView("Topic deleted successfully.");
//    }
//
//    @PostMapping("/deleteSubscription")
//    public RedirectView deleteSubscription(@RequestParam("subscription") String subscriptionName) {
//        this.pubSubAdmin.deleteSubscription(subscriptionName);
//
//        return buildStatusView("Subscription deleted successfully.");
//    }
//
//    private RedirectView buildStatusView(String statusMessage) {
//        RedirectView view = new RedirectView("/");
//        view.addStaticAttribute("statusMessage", statusMessage);
//        return view;
//    }
}