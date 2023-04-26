package com.ogunkuade.microservicesmanager.customizedloadbalancer;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

@LoadBalancerClient(name="IMAGES-SERVICE", configuration= LoadBalancerConfiguration.class)
public class ImageCustomizedLoadBalancer {
    //
}




