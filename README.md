# ddd-developerweek-2023

## First Things

Questions to ask before we start coding:
1. What makes the system worth writing?
2. Why not buy it off the shelf?
3. Why not outsource it?

Good answers:
1. What makes the system worth writing?
- it delivers competitive advantage
- it drives revenue
- it increases customer satisfaction
2. Why not buy it off the shelf?
- it doesn't exist on any shelf
3. Why not outsource it?
- it is a product not a project
- the business logic is complicated and requires domain expertise

## Domains and Sub Domains

Domain: Conference

Core Subdomains:
- Sessions
- Attendees
- Catering
- Social Media
- Swag
- Sponsors

- Attendee

## Domain Objects and Events

### Domain Objects
- Attendee
- Address
- ConferenceSession

### Events
- RegistrationEvent
- SwagEvent
- CateringEvent
- SessionRegistrationEvent

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
Enable input and output to and from the system. Implement *Hexagonal Architecture*
- RESTAdapter
- KafkaAdapter
- RegistrationEventAdapter
- SwagEventAdapter
- CateringEventAdapter
