package eu.heliovo.registryclient;

/**
 * Client to get access to a HELIO Service Registry.
 * 
 * @author MarcoSoldati
 * 
 */
public interface ServiceRegistryClient {
	/**
	 * Get a list of service descriptors.
	 * 
	 * @return a read-only list of all service descriptors in no particular
	 *         order.
	 * @throws ServiceResolutionException 
	 */
	public ServiceDescriptor[] getAllServiceDescriptors()
			throws ServiceResolutionException;

	/**
	 * Get a service descriptor by name.
	 * 
	 * @param serviceName
	 *            the name of the descriptor to lookup. Must not be null.
	 * @return the descriptor or null if none was found.
	 * @throws ServiceResolutionException 
	 */
	public ServiceDescriptor getServiceDescriptor(HelioServiceName serviceName)
			throws ServiceResolutionException;

	/**
	 * Get all endpoints for an instance of a service.
	 * 
	 * @param descriptor
	 *            the descriptor to use
	 * @param capabilty
	 *            the capability to look for. Will be ignored if null.
	 * @param type
	 *            the type of the access interface. Will be ignored if null.
	 * @return an array of URL pointing to the end points. empty if no endpoints
	 *         have been found.
	 * @throws ServiceResolutionException 
	 */
	public AccessInterface[] getAllEndpoints(ServiceDescriptor descriptor,
			ServiceCapability capabilty, AccessInterfaceType type)
			throws ServiceResolutionException;

	/**
	 * Convenience method to get the "best" endpoint by service name and
	 * capability.
	 * 
	 * @param name
	 *            the name of the service
	 * @param capability
	 *            the capability to look for
	 * @param type
	 *            the desired type of the access interface (SOAP, REST, ...)
	 * @return URL pointing to the WSDL file or null if no endpoint has been
	 *         found.
	 * @throws ServiceResolutionException
	 *             in case the service cannot be found.
	 * @deprecated moved to service helio-clientapi
	 */
 	@Deprecated public AccessInterface getBestEndpoint(HelioServiceName name,
			ServiceCapability capability, AccessInterfaceType type)
			throws ServiceResolutionException;

	/**
	 * Get the endpoint for the "best" endpoint by service descriptor and
	 * capability.
	 * 
	 * @param descriptor
	 *            the descriptor to use
	 * @param capability
	 *            the capability to look for
	 * @param type
	 *            the desired type of the access interface (SOAP, REST, ...)
	 * @return the URL pointing to the WSDL files or null if no endpoint has
	 *         been found.
	 * @throws ServiceResolutionException 
	 * @deprecated moved to service helio-clientapi
	 */
	@Deprecated public AccessInterface getBestEndpoint(ServiceDescriptor descriptor,
			ServiceCapability capability, AccessInterfaceType type)
			throws ServiceResolutionException;

	/**
     * Register a specific access interface of a given service capability.
     * 
     * @param serviceDescriptor
     *            the instance descriptor
     * @param accessInterface
     *            the accessInterface associated with the capability.
     * @return true if the descriptor has not been registered before, false if a
     *         instance of this descriptor already exists.
     */
    public abstract boolean registerServiceInstance(ServiceDescriptor serviceDescriptor, AccessInterface accessInterface);

    /**
     * Register a service descriptor. If the service descriptor already exists
     * it is ignored.
     * 
     * @param helioServiceDescriptor
     *            the service descriptor.
     * @return true if the descriptor has not been registered before. false if a
     *         previous instance of the service already existed.
     */
    public abstract ServiceDescriptor registerServiceDescriptor(ServiceDescriptor helioServiceDescriptor);
}
