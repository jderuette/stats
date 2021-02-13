# stats
Stats functions (for GameDev)


Calculate a value (point) based on steps.
For a base point value, a current (accumulated) point and strategy determine the earned points.

Each strategy have a code, a rule and a name.

# The Fixed Step Coefficient
This strategy weights the number of points earned by an action, based on fixed size slices. For each slice, a weight is provided.
The code of this strategy is "FSC" and parameters are : 
- the bonus for the first time the action is done
- the rounding strategy
- the source of data to check slices from
- the operator to apply to the weight
- a list of composed of : 
    - upper bound slice **not inclusive** (the lower bound is the upper of the previous, or 0 if no previous)
    - weight to apply when in this slice

All rules must have a name.

### rules syntax
**[**rule_name**]** rule_code**[**rules_params**]**

with rules_params : 
**(**first_time_bonnus**)****(**round_strategy**)****(**data_source-weight_operator**)**slice_1-weight_1**|**slice_2-weight_2**|**slice_3-weight_3**|**slice_x-weight_x**|**i-weight_last**]**


## Example 1 : Step based on accumulated points
This strategy is bases on already accumulated point (in the category) by the user. For each slice of points, a earned point is weighted. With a possible bonus for the first time the user perform this action.
Accumulated_points can be earned by other actions (in the same category).

The strategy is [Fixed step coefficient plus firstTime bonus rounded up (accumulated points)] : FSC[(100)(up)(accumulated_points-*)500-2|1000-1.5|2000-1|5000-0.75|10000-0.5|50000-0.25|i-0.01]
- First time +100pt
- from 0 to 500 ==> *2
- from 501 to 1 000 ==> *1.5
- from 1 001 to 2 000 => *1
- from 2 000 to 5 000 = > *0.75
- From 5 001 to 10 000 => *0.5
- from 10 001 to 50 000 => *0.25
- from 50 001 to infinite => *0.01

- All result rounded to top unit.

How many Points earn the user if the  action allow to earn 50 base point ?

#### case A
If the user already accumulated 3 518 point on this category, and perform the action for the third time ?
50 * 0.75 = 37.5 = 38pt. Is new (accumulated) points are : 3 158 + 38 : 3 196.

#### case B
If the user already accumulated 0 point on this category and perform the action for the first time ?
(100) + 50 * 2 = 200pt. Is new (accumulated) points are : 0 + 200 : 200.

#### case C
If he add A user already accumulated 12 571 point on this category and perform the action for the 48th time ?
50 * 0.25 = 12.5pt = 13pt. Is new (accumulated) points are : 12 571 + 13 : 12 584.

#### case D
If the user already accumulated 378 236 point on this category and perform the action for the second time ?
(100) + 50 * 0.01 = 0.5pt = 1pt. Is new (accumulated) points are : 378 236 + 1 : 378 237.

## Example 2 : Step Base on repetition
An other strategy is based on number of time a user perform a specific actions.

The strategy is [Fixed step coefficient plus firstTime bonus rounded down (repetition)] : FSC[(5)(down)(repetition-*)10-1|50-0.75|100-0.5|i-0.001]

- First time +5pt
- from 0 to 10 ==> *1
- from 11 to 50 ==> *0.75
- from 51 to 100 => *0.5
- from 101 to infinite = > *0.001

- All result rounded to down unit.


How many point for user if the action allow to earn 50 base point ? 

#### case A
For a user doing action for the first time
5 + (50 *1) = 55pt.

#### case B
For a user doing action for the second time
50 *1 = 50pt.

#### case C
For a user doing action for the 100th time
50 * 05 = 25.

#### case D
For a user doing action for the 250th time
50 * 0.001 = 0.05pt = 0pt.


## Example 3 : linear 
This strategy allow to earned point using linear strategy
 
The strategy is [linear with first Time bonus, rounded up (accumulated_points)] FSC[(500)(up)(accumulated_points-*)i-1]
- First time +500pt
- from 0 to infinite ==> *1

- All result rounded to top unit.

How many point for user if the action allow to earn 10 base point ? 

#### case A
If the user already accumulated 1 000 point on this category, and perform the action for the first time ?
(500) + 10*1 = 510. s new (accumulated) points are : 1 000 + 510 = 1 510.

#### case B
If the user already accumulated 1 000 000 point on this category, and perform the action for the 10 589th time ?
10 * 1 = 10. s new (accumulated) points are : 1 000 000 + 10 = 1 000 010.

