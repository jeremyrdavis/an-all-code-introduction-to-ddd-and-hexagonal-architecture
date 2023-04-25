# developerweek-ddd-06 Project

## Conference Domains
- Sessions
- Attendees
- Social Media
- Catering
- Swag (Giveaways)

## Domain Objects and Domain Events
Domain objects use a "universal language"
Domain events are events with business value

## Entities and Aggregates
Entities are persistent objects
Aggregates encapsulate multiple persistent objects and interact with the system

- Attendee (aggregate)
- Address
- ConferenceSession

## Value Objects
Not persistent and do not contain business logic
- AttendeeInfo
- ConferenceSessionInfo

## Commands
Trigger an action.  Should be idempotent.

## Domain Services
Expose the domain to other parts of the system and orchestrate functionality.
- AttendeeService

## Adapters
Interface with external systems/users; input and output to and for the system
- REST adapter
- Kafka adapter

## Integration with other Domains
- Cooperation
- Partnershpip
- Shared Kernel
- Customer/Supplier
