package com.howard.www.business.queue.service;

public interface IQueueOfElementService<T> {
	public void appendElementToQueue(T enqueueElement) throws Exception;

	public T obtainElementFromQueue() throws Exception;

	public void initializeQueueData(String queueNameToBeOperated) throws Exception;

	public void initializeQueueDataForSystemStartup(String queueNameToBeOperated) throws Exception;
	
    public abstract void publishHandlingQueueEvent()throws Exception;
}
