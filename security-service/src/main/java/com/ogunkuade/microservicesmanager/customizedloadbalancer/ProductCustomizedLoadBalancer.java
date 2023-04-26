package com.ogunkuade.microservicesmanager.customizedloadbalancer;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

@LoadBalancerClient(name="PRODUCT-SERVICE", configuration= LoadBalancerConfiguration.class)
public class ProductCustomizedLoadBalancer {
    //
}




