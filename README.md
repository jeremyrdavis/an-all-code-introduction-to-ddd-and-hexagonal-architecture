# ddd-developerweek-2023

## Domains and Sub Domains

Domain: Conference

1. What makes the system worth writing?
2. Why not buy it off the shelf?
3. Why not outsource it?

Core Subdomains:
- Sessions
- Attendees
- Catering
- Social Media
- Swag
- Sponsors

## Entities
Persistent objects
- Attendee
- Address

### Aggregate
Top level object
- Attendee

## Events
Things that the business is interested in.  Statements of fact.
- RegistrationEvent
- CateringEvent
- SwagEvent

## Value Objects
Not persistent.  Represents a concept from the domain
- *Events
- AttendeeInfoValueObject

## Commands
Request for action
- RegisterAttendeeCommand

## Repositories
Repository pattern for persistence
- AttendeeRepository

## Domain Services
Expose the domain to the rest of the system.  Enable *Hexagonal Architecture*
- AttendeeService

## Adapters
Enable input and output to and from the system
- RESTAdapter
- KafkaAdapter
- RegistrationEventAdapter
- SwagEventAdapter
- CateringEventAdapter
