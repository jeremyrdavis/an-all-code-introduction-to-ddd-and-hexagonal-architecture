# Domain Driven Design and Hexagonal Architecture

"Domain Driven Design: Tackling Complexity in the Heart of Software" - Eric Evans

## Domains and Subdomains

Domain: Conference
Subdomains:
- Sessions
- Attendees
- Payments
- Swag
- Catering
- Social Media
- Partners

### Core Subdomains

1. What makes the system worth writing?
2. Why not buy it off the shelf?
3. Why not outsource it?

- Attendees

## Ubiquitous Language
The domain should match the business owners' understanding of the system.

## Domain Objects and Events
Domain Objects model the system.  
Events are statements of fact that the business cares about, i.e.:
- someone registered for the conference 
- a session's start time has changed

### Domain Objects
Encapsulate business logic
- Attendee
- Address
- ConferenceSession

### Events
- RegistrationEvent
- CateringEvent
- SwagEvent

### Commands
Trigger an action in the system.  Not persistent

## Entities
Core parts of the domain.  Persistent
- Attendee
- Address

### Aggregate
Top level of an object graph
- Attendee

### Repositories
DDD recommends the repository pattern

## Domain Services
Expose domain functionality to the rest of the system.  Enable *Hexagonal Architecture*
- AttendeeService

## Adapters
Inputs to the system and outputs to other systems
- RESTAdapter (input)
- KafkaAdapter (input)
- RegistrationEventAdapter (output)