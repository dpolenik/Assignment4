package com.dan.polenik.reinforcement.learning.world.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("reinforcement.learning.active")
public class RlProperties {
	private boolean valueIteration;
	private boolean policyIteration;
	private boolean qLearning;
	private boolean easyWorld;
	private boolean hardWorld;
	public boolean isValueIteration() {
		return valueIteration;
	}
	public void setValueIteration(boolean valueIteration) {
		this.valueIteration = valueIteration;
	}
	public boolean isPolicyIteration() {
		return policyIteration;
	}
	public void setPolicyIteration(boolean policyIteration) {
		this.policyIteration = policyIteration;
	}
	public boolean isqLearning() {
		return qLearning;
	}
	public void setqLearning(boolean qLearning) {
		this.qLearning = qLearning;
	}
	public boolean isEasyWorld() {
		return easyWorld;
	}
	public void setEasyWorld(boolean easyWorld) {
		this.easyWorld = easyWorld;
	}
	public boolean isHardWorld() {
		return hardWorld;
	}
	public void setHardWorld(boolean hardWorld) {
		this.hardWorld = hardWorld;
	}
	
}
