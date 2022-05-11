package org.ipg.namesvc.web;

import org.ipg.namesvc.svc.PubSubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class PubSubController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PubSubController.class);

    private final PubSubService pubSubService;

    public PubSubController(PubSubService pubSubService) {
        this.pubSubService = pubSubService;
    }

    @GetMapping("/topics")
    public List<String> getTopics() {
        return pubSubService.getAllTopics();
    }

    @GetMapping("/subscriptions")
    public List<String> getSubscriptions() {
        return pubSubService.getAllSubscriptions();
    }

    @PostMapping("/createTopic")
    public RedirectView createTopic(@RequestParam("topicName") String topicName) {
        this.pubSubService.createTopic(topicName);

        return buildStatusView("Topic creation successful.");
    }

    @PostMapping("/createSubscription")
    public RedirectView createSubscription(
            @RequestParam("topicName") String topicName,
            @RequestParam("subscriptionName") String subscriptionName) {
        pubSubService.createSubscription(topicName, subscriptionName);

        return buildStatusView("Subscription creation successful.");
    }

    @GetMapping("/postMessage")
    public RedirectView publish(
            @RequestParam("topicName") String topicName,
            @RequestParam("message") String message,
            @RequestParam("count") int messageCount) {
        for (int i = 0; i < messageCount; i++) {
            this.pubSubService.publish(topicName, message);
        }

        return buildStatusView("Messages published asynchronously; status unknown.");
    }


    @GetMapping("/subscribe")
    public RedirectView subscribe(@RequestParam("subscription") String subscriptionName) {
        this.pubSubService.addSubscriber(subscriptionName);
        return buildStatusView("Subscribed.");
    }

    @PostMapping("/deleteTopic")
    public RedirectView deleteTopic(@RequestParam("topic") String topicName) {
        this.pubSubService.deleteTopic(topicName);

        return buildStatusView("Topic deleted successfully.");
    }

    @PostMapping("/deleteSubscription")
    public RedirectView deleteSubscription(@RequestParam("subscription") String subscriptionName) {
        this.pubSubService.deleteSubscription(subscriptionName);
        return buildStatusView("Subscription deleted successfully.");
    }

    private RedirectView buildStatusView(String statusMessage) {
        RedirectView view = new RedirectView("/");
        view.addStaticAttribute("statusMessage", statusMessage);
        return view;
    }
}